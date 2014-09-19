$(function() {
    $('#create-form').submit(function(event) {
        var regex = /^htt(p|ps):\/\/.+/
        var value = $('#url-input').val();
        if(!regex.test(value)) {
            $('#create-form').addClass('has-error')
            event.preventDefault();
        }
    })
});