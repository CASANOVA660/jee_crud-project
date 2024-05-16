<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Display Users</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* Optional: Add custom CSS here */
        /* Adjust table styles if necessary */
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>User Information</h2>
        <table class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>prenom</th>
                    <th>Email</th>
                    <th>Password</th>
                    <th>Image</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${userList}">
                    <tr>
                        <td>${user.idUser}</td>
                        <td>${user.name}</td>
                        <td>${user.prenom}</td>
                        <td>${user.email}</td>
                        <td>${user.password}</td>
                        <td>
    <c:if test="${not empty user.image}">
        <img src="${request.contextPath}/image/${user.image}" alt="User Image" style="max-width: 100px;">
    </c:if>
</td>
                        <td>
                            <a href="UpdateUserServlet?id=${user.idUser}" class="btn btn-primary">Update</a>
                            <a href="DeleteUserServlet?id=${user.idUser}" class="btn btn-danger">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        
        <!-- Display user count in card -->
<div class="card text-white bg-success mt-4" style="max-width: 20rem;">
        <div class="card-header">Total Users</div>
        <div class="card-body">
            <h5 class="card-title">${userCount} users</h5>
        </div>
        
 
        
    </div>
    </div>
    <!-- Include Bootstrap JS -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
