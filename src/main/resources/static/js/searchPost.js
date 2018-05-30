$(document).ready(function(){
    //prende elemento con id=search
    $('#search').keyup(function(){
        var received = this.value;
        //leggo contesto
        var context = $('base').attr('href');
        if(received.length > 2 || received.length == 0){
            //invio richiesta ajax da gestire nel controller, riceverà un parametro
            $.ajax({
                url: context + 'blogpost/search?q=' + received,
                type: "GET",
                dataType:"json"
            })

            .done(function(data) {
                $('#articles_container').remove();
                var newContent = $('#ajax_container');
                newContent.empty();
                $(data).each(function (index, element) {
                    console.log("element: " + element.id);
                    newContent.append("<article class = 'col-6 col-lg-4 text-center'>" +
                        "<h2 tabindex='8'>" + element.title + "</h2>" +
                        "<p>Text:</p>" +
                        "<p>" + element.text + "</p>" +
                        "<p>Author:</p>" +
                        "<p>" + element.user + "</p>" +
                        "<p>Category:</p>" +
                        "<p>" + element.category + "</p>" +
                        "<p>" +
                            "<a role='button' tabindex='8' class='btn btn-secondary' href='" + context + "blog/" + element.id +"'>Details page</a>" +
                        "</p>" +
                        "<br>" +
                        "</article>");
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