$(document).ready(function(){
    $('#search').keyup(function(){
        var received = this.value;
        var context = $('base').attr('href');
        if(received.length >= 0){
            //console.log('/blogpost/search?q=' + received);
            $.ajax({
                url: context + 'blogpost/searchuser?q=' + received,
                type: "GET",
                dataType:"json"
            })

                .done(function(data) {
                    $('#users_container').remove();
                    var newContent = $('#ajax_container');
                    newContent.empty();
                    $(data).each(function (index, element) {
                        //console.log("element: " + element.id);
                        newContent.append("<div class = 'col-6 col-lg-4 text-center'>" +
                            "<h2 tabindex='8'>" + element.username + "</h2>" +
                            "<p>Name:</p>" +
                            "<p>" + element.name + "</p>" +
                            "<p>Surname:</p>" +
                            "<p>" + element.surname + "</p>" +
                            "<p>Role:</p>" +
                            "<p>" + element.role + "</p>" +
                            "<br>" +
                            "</div>");
                        //altrimenti anche chi non è loggato vede il bottone
                        //"<a class='btn btn-primary' tabindex='8' role='button' sec:authorize='hasRole('ADMIN')' th:href='@{'/user/' + ${user.username} + '/delete'}>Remove user</a>" +
                    })
                    $('#main_container').append(newContent);
                })
                .fail(function() {
                    alert('Error');
                });
        }else{
            return;
        }
    });
});