<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${announcementsContent != null && !announcementsContent.isEmpty()}">
    <div id="announcement">
        ${announcementsContent}
    </div>
</c:if>