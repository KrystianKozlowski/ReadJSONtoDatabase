<%@ include file="/WEB-INF/jsp/includetags.jsp" %>
<html>
   <head>
      <title>5 Most Freequent Crimes</title>
   </head>

   <body>
      <h2>5 Most Freequent Crimes</h2>
      <form:form method = "POST" action = "/jsonreader/chart" modelAttribute="freequentCrime">
         <table>
            <tr>
               <td>Year</td>
               <td><form:input path = "year" /></td>
            </tr>
            <tr>
               <td>LocalizationId</td>
               <td><form:input path = "localizationId" /></td>
            </tr>
            <tr>
               <td colspan = "1">
                  <input type = "submit" class="button" name="json" value = "Get JSON"/>
               </td>
			   <td colspan = "1">
                  <input type = "submit" class="button" name="chart" value = "Get Chart"/>
               </td>
            </tr>
         </table>
      </form:form>
   </body>


</html>