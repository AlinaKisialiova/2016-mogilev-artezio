$(function() {

    $chooseBlock = $('#chooseBlock');
    $webcamBlock = $('#webcamBlock');
    $fileBlock = $('#fileBlock');

    $chooseBlock.find('.chooseWebcamBlock').on('click', openWebcamBlock);
    $chooseBlock.find('.chooseFileBlock').on('click', openFileBlock);

    $('#webcamImageSend').on('click', function () {
        $form = $('#webcamImageForm');
        $form.submit();
    });

    $('#webcamReset').on('click', function() {
        window.location.reload();
    });

    function openFileBlock() {
        $fileBlock.show();
        $chooseBlock.hide();
    }

    function openWebcamBlock() {
        $chooseBlock.hide();
        $webcamBlock.show();
    }

});

