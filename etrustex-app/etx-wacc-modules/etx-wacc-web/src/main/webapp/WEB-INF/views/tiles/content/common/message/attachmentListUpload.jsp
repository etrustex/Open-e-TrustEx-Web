<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://etrustex.ec.europa.eu/functions" %>

<c:set var="ver">@eTrustEx.version@-@eTrustEx.timestamp@</c:set>
<h3><spring:message code="label.listOfFiles"/></h3>

<div class="left">
    <img src="<c:url value="/images/img/icon_upload.png"/>" alt="Upload files" width="45" height="45"/>
</div>

<link href="<c:url value="/css/uploadPanel.css?ver=${ver}"/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/css/fileupload/jquery.fileupload.css?ver=${ver}"/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/css/fileupload/jquery.fileupload-ui.css?ver=${ver}"/>" rel="stylesheet" type="text/css"/>

<input id="businessName" type="hidden" value="${businessName}"/>
<div class="main-panel">
    <div class="upload-header-footer-container">
        <span class="upload-header-select">
            <span class="upload-total-files">
               <spring:message code="label.files.to.upload"/><span class="inline" id="filesToUpload"></span>
            </span>
            <span class="upload-total-files">
                <spring:message code="label.total.files"/><span class="inline" id="totalFiles"></span>
            </span>
            <label for="hide-show-sucessful"><spring:message code="label.hideUploaded"/></label>
            <input id="hide-show-sucessful" type="checkbox" />
        </span>
    </div>
    <div class="panel panel-default upload-panel">
        <table id="fileList" class="table generic-table" >
        </table>
        <%--<tilesx:useAttribute name="business"/>--%>
    </div>

    <div class="upload-header-footer-container">
        <span id="upload-total-files-error" style="display: none;color: red">
            <spring:message code="message.upload.failed.exceed.maximum.files"/>
        </span>
        <span class="inline upload-header-select">
            <span class="inline">
                <spring:message code="label.totalSize"/>&nbsp;
            </span>
            <span class="inline" id="uploadSize"></span>
        </span>
    </div>
    <div class="row fileupload-buttonbar">
        <div class="col-lg-7">
            <!-- The fileinput-button span is used to style the file input field as button -->
            <span class="btn btn-info btn-outline btn-sm fileinput-button">
                <i class="glyphicon glyphicon-plus"></i>
                <span><spring:message code="label.add.files"/></span>
                <input type="file" name="files[]" id="addFiles" class="addFiles" multiple>
            </span>
            <button id="addFolder" type="button" class="btn btn-info btn-outline btn-sm folderinput-button">
                <i class="glyphicon glyphicon-plus"></i>
                <span><spring:message code="label.add.folder"/></span>
            </button>
            <%-- Files are duplicated wjem using 2 file upload widgets
            <span class="btn btn-success fileinput-button">
                <i class="glyphicon glyphicon-plus"></i>
                <span>Add folder</span>
                <input type="file" name="files2[]" id="addFolder" class="addFiles" webkitdirectory directory>
            </span>--%>

            <button id="uploadFiles" type="button" class="btn btn-primary btn-outline btn-sm start">
                <i class="glyphicon glyphicon-upload"></i>
                <span><spring:message code="label.start.upload"/></span>
            </button>

            <button id="resetUploadFiles" type="button" class="btn btn-danger btn-outline btn-sm delete">
                <i class="glyphicon glyphicon-trash"></i>
                <span><spring:message code="label.remove.all.files"/></span>
            </button>
        </div>
    </div>
</div>

<script type="text/javascript" language="javascript" src="<c:url value="/js/lib/jquery.ui.widget.js?ver=${ver}"/>"></script>
<script type="text/javascript" language="javascript" src="<c:url value="/js/lib/fileupload/jquery.iframe-transport.js?ver=${ver}"/>"></script>
<script type="text/javascript" language="javascript" src="<c:url value="/js/lib/fileupload/jquery.fileupload.js?ver=${ver}"/>"></script>
<script>
  let forbiddenUploadFileExtensions = '${forbiddenUploadFileExtensions}' ? '${forbiddenUploadFileExtensions}' : false
</script>
