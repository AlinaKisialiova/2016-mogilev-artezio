(function () {
    var $takeTaskForm = $('form#takeTaskForm');
    var $submitButton = $takeTaskForm.find('button[type=submit]');

    $takeTaskForm.find('.advice-check').on('click', toggleAdvice);

    function toggleAdvice() {
        var $checkButton = $(this);
        var isActive = $checkButton.hasClass('active');

        $checkButton.toggleClass('active')
            .toggleClass('btn-primary')
            .toggleClass('btn-default')
            .closest('tr').toggleClass('info')
            .find('input').prop('checked', !isActive);
        checkSubmitAvailability();
    }

    function checkSubmitAvailability() {
        var adviceCount = $takeTaskForm.find('input[type=checkbox]:checked').length;
        $submitButton.attr('disabled', adviceCount === 0);
    }
})();
