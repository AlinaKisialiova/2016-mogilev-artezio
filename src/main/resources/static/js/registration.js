(function () {
    var $readyButton = $('#readyButton');
    var $nonEqualPassword = $('#nonEqualPassword');
    var $password = $('#password');
    var $retryPassword = $('#retryPassword');

    $password.on('change', passwordChanged);
    $retryPassword.on('change', passwordChanged);

    function passwordChanged() {
        if ($password.val() != $retryPassword.val()) {
            $nonEqualPassword.text('Пароли не совпадают');
            $readyButton.prop('disabled', true);
        } else {
            $readyButton.prop('disabled', false);
            $nonEqualPassword.text('');
        }
    }
})();