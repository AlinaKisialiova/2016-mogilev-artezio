(function () {

    var $webcamBlock = $('#camera-block');
    var $webcamShotView = $('.camera-shot-view');
    var $webcamShotBtn = $('.camera-shot-btn');

    $webcamShotBtn.on('click', takeShot);

    setUpCamera();

    function setUpCamera() {
        Webcam.set({
            width: $(window).width()*0.7,
            height: $(window).height()*0.7,
            image_format: 'jpeg',
            jpeg_quality: 100
        });
        Webcam.attach('#camera-block');
    }

    function takeShot() {
        Webcam.snap(function(data_uri) {
            $webcamShotView.attr('src', data_uri);
            var base64Data = Webcam.dataUrlToRawBase64(data_uri);
            $('input[name=imageBase64]').val(base64Data);
        });
    }


    function abso() {

        $('.container').collapse();

        $('.wrapper').css({
            position: 'absolute',
            top: 0,
            left: 0,
            zIndex: 999,
            background: 'rgba(1, 1, 1, 0.9)',
            width: '100%',
            height: '100%'
        });

        $('#camera-block').find("video").css({
            position: 'absolute',
            top: 40,
            left: 0,
            width: '100%',
            height: 'calc(100% - 40px)'
        });
    }

    $(window).resize(function() {
        abso();
    });

    $('.wrapper').on('click', function() {
        $('.wrapper').fadeOut(200, function() {
            this.css({
                position: 'absolute',
                top: 0,
                left: 0,
                display: 'none'
            });
        });

    });

    abso();

})();
