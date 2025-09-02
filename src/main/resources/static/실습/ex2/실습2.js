const client = new WebSocket("/alarm");

client.onopen = (event) => {
    console.log('접속');
}

client.onclose = (event) => {
    console.log('퇴장');
}

client.onmessage = (event) => {
    alert(event.data);
}