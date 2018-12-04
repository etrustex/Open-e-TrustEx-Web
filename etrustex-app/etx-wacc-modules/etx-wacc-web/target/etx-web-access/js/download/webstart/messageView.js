function pingForDownload() {
    pingIntervalId = window.setInterval( function(){
        ping(pingUrl,
            null,
            function () {
                stopPinging();
                window.location.href = partyUrl;
            })
    }, 10 * 60 * 1000);
}
