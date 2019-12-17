export let treeFolderStructure = [];

export function arrangeIntoTree(fileInfo) {

  const path = toPath(fileInfo);
  let childrenInCurrentNode = treeFolderStructure;

  path.forEach((folderName) => {
    const currentNode = childrenInCurrentNode.filter(child => child.name === folderName);

    if (currentNode.length > 0) {
      childrenInCurrentNode = currentNode[0].children;
    } else {
      const newNode = newFolderNode(folderName);
      childrenInCurrentNode.push(newNode);
      childrenInCurrentNode = newNode.children;
    }
  });

  childrenInCurrentNode.push(newFileNode(fileInfo))
}

function toPath(fileInfo) {
  let strPath = fileInfo.filePath == undefined ? fileInfo.path :  fileInfo.filePath;
  strPath = strPath.replace(/\/$/, "");
  return strPath == "" ?  [] :  strPath.split('/')
}

function newFileNode(fileInfo) {
    return {
      name: fileInfo.fileName,
      isDir: false,
      fileInfo:fileInfo
    }
}

function newFolderNode(folderName) {
  return {
    name: folderName,
    children: [],
    isDir: true
  }
}

export function cleanTree() {
  treeFolderStructure = []
}
