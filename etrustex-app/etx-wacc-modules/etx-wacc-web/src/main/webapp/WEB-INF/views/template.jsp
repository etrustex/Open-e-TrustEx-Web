<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>

<!DOCTYPE html>
<c:set var="ver">@eTrustEx.version@-@eTrustEx.timestamp@</c:set>
<html>
<head>
    <script type="text/javascript" language="javascript"
            src="<c:url value="/js/lib/sockjs.js?ver=${ver}"/>">//Source: https://cdnjs.cloudflare.com</script>
    <script type="text/javascript" language="javascript"
            src="<c:url value="/js/lib/stomp.js?ver=${ver}"/>">//Source: https://cdnjs.cloudflare.com</script>

	<title>e-TrustEx</title>


    <link id="ctxRootId" href="<c:url value="/"/>" rel="dns-prefetch"/>

    <link rel="stylesheet" href="<c:url value="/css/bootstrap/bootstrap.min.css?ver=${ver}"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap/buttons.css?ver=${ver}"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/redmond/jquery-ui.css?ver=${ver}"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/redmond/etx-jquery-ui.css?ver=${ver}"/>"/>


    <link href="<c:url value="/css/main.css?ver=${ver}"/>" rel="stylesheet" type="text/css" />

    <link href="<c:url value="/css/bootstrap/vendor/bootstrap-combobox.css?ver=${ver}"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/css/bootstrap/progress.css?ver=${ver}"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/css/bootstrap_tree.css?ver=${ver}"/>" rel="stylesheet" type="text/css"/>

    <tilesx:useAttribute name="cssPath"/>

    <c:if test="${not empty cssPath}">
        <link href="<c:url value="${cssPath}?ver=${ver}"/>" rel="stylesheet" type="text/css" />
    </c:if>

    <link href="<c:url value="/css/customization_bootstrap.css?ver=${ver}"/>" rel="stylesheet" type="text/css"/>

    <tiles:insertAttribute name="i18"/>

    <script type="text/javascript" language="javascript" src="<c:url value="/js/lib/jquery-1.11.1.min.js?ver=${ver}"/>">//Source: https://jquery.com/</script>
    <script type="text/javascript" language="javascript" src="<c:url value="/js/lib/is.min-0.9.0.js?ver=${ver}"/>">//Source: http://is.js.org/</script>
    <script type="text/javascript" language="javascript" src="<c:url value="/js/lib/bootstrap.min.js?ver=${ver}"/>"></script>
    <script type="text/javascript" language="javascript" src="<c:url value="/js/lib/bootstrap-combobox_etrustex.js?ver=${ver}"/>"></script>
    <script type="text/javascript" language="javascript" src="<c:url value="/js/lib/jquery-ui.min-1.11.4.js?ver=${ver}"/>">//Source: https://jqueryui.com/</script>
    <script type="text/javascript" language="javascript" src="<c:url value="/js/lib/jquery.blockUI.min.js?ver=${ver}"/>"></script>

    <tiles:importAttribute name="jsPaths"/>

    <c:forEach var="jsPath" items="${jsPaths}">
        <script type="text/javascript" language="javascript" src="<c:url value="${jsPath}?ver=${ver}"/>"></script>
    </c:forEach>

</head>
<body>
    <div id="disabling-div"></div>

    <noscript>
        <div class="no-js">Impossible to use this application without Javascript. Please contact your Administrator</div>
    </noscript>
    <div class="wrapper">
        <div class="container">
            <tiles:insertAttribute name="cookieBanner" />
            <tiles:insertAttribute name="announcement" />
            <tiles:insertAttribute name="header" />
            <tiles:insertAttribute name="navHeader" />
            <tiles:insertAttribute name="sidebar" />
            <tiles:insertAttribute name="content" />
        </div>
    </div>
    <tiles:insertAttribute name="footer" />
</body>
</html>
