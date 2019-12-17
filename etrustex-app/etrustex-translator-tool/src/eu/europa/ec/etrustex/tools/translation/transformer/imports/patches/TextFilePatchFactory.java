package eu.europa.ec.etrustex.tools.translation.transformer.imports.patches;

import eu.europa.ec.etrustex.tools.translation.transformer.imports.EditingPatch;
import eu.europa.ec.etrustex.tools.translation.transformer.imports.EditingPatchFactory;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A text file based factory for editing patches.
 *
 * @author keschma
 *
 */
public class TextFilePatchFactory implements EditingPatchFactory {
	
	private final static Logger LOGGER = Logger.getLogger(TextFilePatchFactory.class);
	
	final Path patchFile;
	
	/**
	 * Constructor.
	 * @param fileName the name of the patch file
	 */
	public TextFilePatchFactory(String fileName) {
		patchFile = Paths.get(fileName);
		
		// some sanity checks
		if (Files.notExists(patchFile)) {
			throw new IllegalArgumentException("File \"" + fileName + "\" does not exist.");
		}
		if (!Files.isRegularFile(patchFile)) {
			throw new IllegalArgumentException("File \"" + fileName + "\" is not a file.");
		}
		if (!Files.isReadable(patchFile)) {
			throw new IllegalArgumentException("File \"" + fileName + "\" is not readable.");
		}
	}
	
	@Override
	public List<EditingPatch> getPatches() {		
		try {
			return parsePatches(patchFile);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	/**
	 * Parses all patches defined in the given file.
	 * @param patchFile the patch file object
	 * @return the parsed patches
	 * @throws IOException on IO exceptions while handling the patch file
	 */
	private List<EditingPatch> parsePatches(Path patchFile) throws IOException {
		final List<EditingPatch> patches = new ArrayList<>();

		try(BufferedReader reader = Files.newBufferedReader(patchFile, Charset.defaultCharset())) {
			int lineNumber = 1;
			String line;

			while ((line = reader.readLine()) != null) {
				final String trimmedLine = line.trim();
				if (!trimmedLine.isEmpty()) {
					final AbstractEditingPatch patch = parse(trimmedLine, lineNumber);
					LOGGER.info("Loading editing operation patch: " + patch);
					patches.add(patch);
				}
				lineNumber++;
			}
		}

		return patches;
	}
	
	/**
	 * Parses a line defining a patch and produces the corresponding object.
	 * @param line the text line
	 * @param lineNumber the line number
	 * @return the patch
	 * @throws IllegalArgumentException on parse errors
	 */
	private AbstractEditingPatch parse(String line, int lineNumber) {
		AbstractEditingPatch patch = null;
		
		final String[] words = line.split("\\s+");
		if (words.length == 0) {
			lineError("No patch ID found", line, lineNumber);
		}
		final String[] arguments = Arrays.copyOfRange(words, 1, words.length);
		
		final String patchId = words[0].toLowerCase();
		if (patchId.equals(DeletePatch.PATCH_ID)) {
			patch = new DeletePatch(); 
		} else if (patchId.equals(RenamePatch.PATCH_ID)) {
			patch = new RenamePatch();
		} else {
			lineError("No patch implemented for ID=" + patchId, line, lineNumber);
			return null;
		}
		
		patch.initFrom(arguments);	
		return patch;
	}
	
	private void lineError(String message, String line, int lineNumber) {
		throw new IllegalArgumentException("[line #" + lineNumber + " - \"" + line + "\"] " + message);
	}

}
