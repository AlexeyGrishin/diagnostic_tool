<%@ page import="com.actimind.diagnostic.view.ViewHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${param['js']!=1}" >
    </div>
    <span class="force">
        <button class="force-button btn force" href="<%= ViewHelper.linkToForce(true) %>">Collect Now</button>
        <button class="clear-button btn force"  href="<%= ViewHelper.linkToDeleteAll() %>">Clear and Collect Now</button>
    </span>
    
</body>
</html>
</c:if>