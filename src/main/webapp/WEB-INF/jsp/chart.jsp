<%@ include file="/WEB-INF/jsp/includetags.jsp" %>
<html>
   <head>
      <title>5 Most Freequent Crimes</title>
   </head>

   <body>
       <h2>Most Freequent Crimes</h2>
         <table>
            <tr>
               <td>Year:</td>
               <td>${year}</td>
            </tr>
            <tr>
               <td>Localization:</td>
               <td>${localizationId}</td>
            </tr>
         </table>
         <img src="resources/charts/PieChart.jpeg">
         </br>
         <a href="http://localhost:8080/jsonreader/main">POWRÓT NA STRONĘ GŁÓWNĄ</a>

   </body>

</html>