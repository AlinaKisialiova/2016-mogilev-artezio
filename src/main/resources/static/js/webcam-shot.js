(function () {

    var $webcamBlock = $('.camera-block');
    var $webcamShotView = $('.camera-shot-view');
    var $webcamShotBtn = $('.camera-shot-btn');

    $webcamShotBtn.on('click', takeShot);

    setUpCamera();

    function setUpCamera() {
        Webcam.set({
            width: 320,
            height: 240,
            image_format: 'jpeg',
            jpeg_quality: 90
        });
        Webcam.attach('.camera-block');
    }

    function takeShot() {
        Webcam.snap(function(data_uri) {
            $webcamShotView.attr('src', data_uri);
            $('input[name=imageBase64]').val(Webcam.dataUrlToRawBase64(data_uri));
        } );
    }

})();
