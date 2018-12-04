
        <table id="fileList" class="table generic-table" >

        </table>


        <script>

            var fileListTableRow =
                '<tr>' +
                '<td style="visibility:{0}" class="payload"></td>'+
                '<td> {1} </td>'+
                '<td> {2} </td>'+
                '</tr>';


            function updateList(uploadedFileList) {
                for (var i = 0; i < uploadedFileList.length; i++) {
                    var file =uploadedFileList[i];
                    if (file.isPayload) {
                        var row = fileListTableRow.format("visible", file.fileName, file.formattedSize);
                    }
                    else {
                        var row = fileListTableRow.format("hidden", file.fileName, file.formattedSize);
                    }
                    $('#fileList').append(row);
                }


            }
        </script>