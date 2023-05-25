<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <title>CRUD Operations</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
  $(document).ready(function() {
    // Function to handle AJAX call and display response
    function Request(url) {
      $.ajax({
        url: url,
        method: 'GET',
        success: function(response) {
          $('#content').html(response);
        },
        error: function(xhr, status, error) {
          console.log('AJAX Error: ' + error);
        }
      });
    }

    // Event handlers for CRUD buttons
    $('#createBtn').click(function() {
      Request('RecordServlet?action=create');
    });

    $('#readBtn').click(function() {
      Request('RecordServlet?action=read');
    });

    $('#updateBtn').click(function() {
      Request('RecordServlet?action=update');
    });

    $('#deleteBtn').click(function() {
      Request('RecordServlet?action=delete');
    });
  });
</script>

</head>
<body>
  <div class="container mt-5" align="center">
    <h2>CRUD Operations</h2>
    <div class="text-center my-3">
      <button id="createBtn">Create</button>
      <button id="readBtn">Read</button>
      <button id="updateBtn">Update</button>
      <button id="deleteBtn">Delete</button>
    </div>
    <div id="content"></div>
  </div>
</body>
</html>
