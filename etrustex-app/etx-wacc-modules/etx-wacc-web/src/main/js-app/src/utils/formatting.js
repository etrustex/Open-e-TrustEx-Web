let fileSizeUnits = ["B", "KiB", "MiB", "GiB"]
let fileSizeCaps = [1, 1024, 1048576, 1073741824 ]
export let formatFileSize = (sizeInBytes) => {

  let index = 0;

  while(fileSizeCaps[index+1] < sizeInBytes && index+1 < fileSizeUnits.length) {
    index++
  }
  let sizeToDisplay = (sizeInBytes/fileSizeCaps[index]).toFixed(2)
  return `${sizeToDisplay} ${fileSizeUnits[index]}`

}