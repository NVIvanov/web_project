function createUser() {
    var name = document.getElementById('username').value + '';
    $.ajax({
        type: 'POST',
        url: '/users/',
        contentType: 'application/json',
        data: JSON.stringify({
            username: name,
            password: name + name,
            name: name
        }),
        success: function() {
            window.location.href = '/' + name;
        }
    });
}