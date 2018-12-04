
<table id="fileList" class="table edma-table" >


        </table>

<script>

    var fileListTableRow =
        '<tr>' +
        '<td> {0} </td>'+
        '<td> {1} </td>'+
        '<td><img src="{2}" title="{3}"/></td>'+
        '<td><img src="{4}" title="{5}"/></td>'+
        '</tr>';

    function updateList(uploadedFileList) {
        for (var i = 0; i < uploadedFileList.length; i++) {
            var file = uploadedFileList[i];
            var confidentialImg = "";
            var confidentialLabel = "";
            var commentImg = url('/images/icons/comment.png');
            var commentLabel = "";

            if (file.confidential) {
                confidentialImg = url('/images/icons/confidential.png');
                confidentialLabel = '<spring:message code="uploadPanel.confidentialToolTip.text" javaScriptEscape="true"/>';
            } else {
                confidentialImg = url('/images/icons/non-confidential.png');
                confidentialLabel = '<spring:message code="uploadPanel.nonConfidentToolTip.text" javaScriptEscape="true"/>';
            }

            if (file.comment != null) {
                commentLabel = file.comment;
            }

            var row = fileListTableRow.format(file.fileName, file.formattedSize, confidentialImg, confidentialLabel, commentImg, commentLabel);
            $('#fileList').append(row);
        }


    }

</script>