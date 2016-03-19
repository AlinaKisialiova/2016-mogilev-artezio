(function () {
    var $document = $(document);
    var $readyButton = $('#readyButton');
    var $nonEqualPassword = $('#nonEqualPassword');
    var $password = $('#password');
    var $retryPassword = $('#retryPassword');
    var $birthDate = $('#birthDate');
    var $datepicker = $('.datepicker').datepicker({minDate: 0});

    $password.on('change', passwordChanged);
    $retryPassword.on('change', passwordChanged);
    $document.on('ready', $datepicker);
    $birthDate.on('change', birthDateChanged);

    function passwordChanged() {
        if ($password.val() != $retryPassword.val()) {
            $nonEqualPassword.text('Пароли не совпадают');
            $readyButton.prop('disabled', true);
        } else {
            $readyButton.prop('disabled', false);
            $nonEqualPassword.text('');
        }
    }

    function birthDateChanged() {
        $birthDate.datepicker('update', $(this).val());
    }
})();