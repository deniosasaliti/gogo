(function (red5prosdk) {

    // Create a new instance of the WebRTC publisher.
    var publisher = new red5prosdk.RTCPublisher();

    // Initialize
    publisher.init({
        protocol: 'ws',
        port: 5080,
        host: 'localhost',
        app: 'live',
        streamName: 'mystream',
        rtcConfiguration: {
            iceServers: [{urls: 'stun:stun2.l.google.com:19302'}],
            iceCandidatePoolSize: 2,
            bundlePolicy: 'max-bundle'
        }, // See https://developer.mozilla.org/en-US/docs/Web/API/RTCPeerConnection/RTCPeerConnection#RTCConfiguration_dictionary
        streamMode: 'live',
        mediaElementId: 'red5pro-publisher',
        bandwidth: {
            audio: 56,
            video: 512
        },
        mediaConstraints: {
            audio: true,
            video: {
                width: {
                    exact: 640
                },
                height: {
                    exact: 480
                },
                frameRate: {
                    min: 8,
                    max: 24
                }
            }
        }
    })
        .then(function() {
            // Invoke the publish action.
            return publisher.publish();
        })
        .catch(function(error) {
            // A fault occurred while trying to initialize and publish the stream.
            console.error(error);
        });

})(window.red5prosdk);