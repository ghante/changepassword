<%@ include file="header.jsp" %>
<h1>Your reserved items</h1>
<h3>Item reservation pending......</h3>


<table id="prettyTable">
        <thead>
            <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Description</th>
                <th>Type</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>${item.name}</td>
                <td>${item.price}</td>
                <td>${item.description}</td>
                <td>${item.type}</td>
            </tr>
        </tbody>
</table>
