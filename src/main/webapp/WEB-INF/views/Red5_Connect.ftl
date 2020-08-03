<!doctype html>
<html>
<head>
    <!-- *Recommended WebRTC Shim -->
    <script src="https://webrtchacks.github.io/adapter/adapter-latest.js"></script>
</head>
<body>
<!-- video containers -->
<!-- publisher -->
<div>
    <video id="red5pro-publisher" width="640" height="480" muted autoplay></video>
</div>
<!-- subscriber -->
<div>
    <video id="red5pro-subscriber" width="640" height="480" controls autoplay></video>
</div>
<!-- Red5 Pro SDK -->
<script src="lib/red5pro/red5pro-sdk.min.js"></script>
<!-- Create Pub/Sub -->
<script>
    (function(red5prosdk) {
        'use strict';

        var rtcPublisher = new red5prosdk.RTCPublisher();
        var rtcSubscriber = new red5prosdk.RTCSubscriber();
        var config = {
            protocol: 'ws',
            host: 'localhost',
            port: 5080,
            app: 'live',
            streamName: 'mystream',
            rtcConfiguration: {
                iceServers: [{urls: 'stun:stun2.l.google.com:19302'}]
            }
        };

        function subscribe () {
            rtcSubscriber.init(config)
                .then(function () {
                    return rtcSubscriber.subscribe();
                })
                .then(function () {
                    console.log('Playing!');
                })
                .catch(function (err) {
                    console.log('Could not play: ' + err);
                });
        }

        rtcPublisher.init(config)
            .then(function () {
                // On broadcast started, subscribe.
                rtcPublisher.on(red5prosdk.PublisherEventTypes.PUBLISH_START, subscribe);
                return rtcPublisher.publish();
            })
            .then(function () {
                console.log('Publishing!');
            })
            .catch(function (err) {
                console.error('Could not publish: ' + err);
            });

    }(window.red5prosdk));
</script>
</body>
</html>