<html>
<head>
    <!-- Recommended shim for cross-browser WebRTC support. -->
    <script src="https://webrtchacks.github.io/adapter/adapter-latest.js"></script>
    <title>1</title>
</head>
<body>
<!-- `autoplay` will immediately show preview video. `muted` will mute the audio to avoid feedback noise. -->
<video id="red5pro-publisher" autoplay muted></video>
<!-- Exposes `red5prosdk` on the window global. -->
<script src="lib/red5pro/red5pro-sdk.min.js"></script>
<!-- Example script below. -->
<script src="main.js"></script>
</body>
</html>