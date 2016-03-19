(function () {
    var $takeTaskForm = $('form#takeTaskForm');
    var $submitButton = $takeTaskForm.find('button[type=submit]');
    var $addCustomUserAdvice = $('#addCustomUserAdvice');
    var $customUserAdvice = $('#customUserAdvice');

    $takeTaskForm.find('.advice-check').on('click', toggleAdvice);
    $addCustomUserAdvice.on('click', addCustomAdvice);

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

    function addCustomAdvice() {
        $addCustomUserAdvice.hide();
        $customUserAdvice.find('select').show();
        $customUserAdvice.find('textarea').show();
        $customUserAdvice.find('.addUserAdviceText').hide();
    }
})();

