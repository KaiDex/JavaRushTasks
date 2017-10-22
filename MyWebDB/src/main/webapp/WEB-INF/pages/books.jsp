<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Books Page</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<a href="../../index.jsp">Back to main menu</a>

<br/>
<br/>

<c:url var="searchAction" value="search"/>
<form:form action="${searchAction}">
    <caption><h3>Search a Book</h3></caption>
    <label>
        <input type="text" name="title" id="title">
        <input type="submit" value="Search">
    </label>
</form:form>

<br/>
<br/>

<div class="button-group">
    <c:if test="${!empty listBooks}">
        <table class="tg">
            <caption><h1>Book List</h1></caption>
            <tr>
                <th width="80">ID</th>
                <th width="120">Title</th>
                <th width="200">Description</th>
                <th width="120">Author</th>
                <th width="120">ISBN</th>
                <th width="200">Print Year</th>
                <th width="200">Read Already</th>
                <th width="60">Edit</th>
                <th width="60">Delete</th>
            </tr>
            <c:forEach items="${listBooks}" var="book">
                <tr>
                    <td>${book.id}</td>
                    <td><a href="/bookdata/${book.id}">${book.title}</a></td>
                    <td>${book.description}</td>
                    <td>${book.author}</td>
                    <td>${book.isbn}</td>
                    <td>${book.printYear}</td>
                    <td>${book.readAlready}</td>
                    <td><a href="<c:url value='/editbook/${book.id}'/>">Edit</a></td>
                    <td><a href="<c:url value='/remove/${book.id}'/>">Delete</a></td>
                </tr>
            </c:forEach>

        </table>
    </c:if>

    <c:url var="prevAction" value="prevPage"/>
    <form:form class="paging" action="${prevAction}">
        <label>
            <input type="submit" value="prevPage">
        </label>
    </form:form>

    <c:url var="nextAction" value="nextPage"/>
    <form:form class="paging" action="${nextAction}">
        <label>
            <input type="submit" value="nextPage">
        </label>
    </form:form>
</div>

<br>

<c:url var="addAction" value="/books/add"/>

<form:form action="${addAction}" commandName="book">
    <table>
            <caption><h1>Add Book</h1></caption>
            <tr>
                <td>
                    <form:label path="author">
                        <spring:message text="Author"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="author"/>
                </td>
            </tr>
        <tr>
            <td>
                <form:label path="title">
                    <spring:message text="Title"/>
                </form:label>
            </td>
            <td>
                <form:input path="title"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="description">
                    <spring:message text="Description"/>
                </form:label>
            </td>
            <td>
                <form:input path="description"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="isbn">
                    <spring:message text="ISBN"/>
                </form:label>
            </td>
            <td>
                <form:input path="isbn"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="printYear">
                    <spring:message text="Print Year"/>
                </form:label>
            </td>
            <td>
                <form:input path="printYear"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
            <input type="submit"
                   value="<spring:message text="Add Book"/>"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
