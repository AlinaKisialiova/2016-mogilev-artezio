(function () {
    var $manageTaskForm = $('form#manageTaskForm');
    var $submitSaveButton = $manageTaskForm.find('button.submit-save');
    var $resetTaskFormButton = $manageTaskForm.find('input.reset');

    $manageTaskForm.find('.advice-check').on('click', toggleAdvice);

    function toggleAdvice() {
        var $checkButton = $(this);
        var isActive = $checkButton.hasClass('active');

        $checkButton.toggleClass('active')
            .closest('tr').toggleClass('info')
            .find('input').prop('checked', !isActive);
        checkSubmitAvailability();
    }

    function checkSubmitAvailability() {
        var adviceCount = $manageTaskForm.find('input[type=checkbox]:checked').length;
        $submitSaveButton.attr('disabled', adviceCount === 0);
    }

    $resetTaskFormButton.on('click', resetTaskForm);

    function resetTaskForm() {
        $manageTaskForm.find('.advice-check').each(function () {
            $(this).removeClass('active')
                .closest('tr').removeClass('info')
                .find('input').prop('checked', false);
        });

        $submitSaveButton.attr('disabled', true);
    }
})();
