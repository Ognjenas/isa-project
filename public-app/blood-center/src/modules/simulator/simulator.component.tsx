// import SockJS from 'sockjs-client';
// import * as Stomp from '@stomp/stompjs';
import { useState } from 'react';
import SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';

export const Simulator = () => {
    const [isLoaded, setIsLoaded] = useState();
    var stompClient: any;

    const openConnection = () => {
        let ws = new SockJS('http://localhost:8000/socket');
        stompClient = Stomp.over(ws);

        stompClient.connect({}, function (frame: any) {
            console.log(frame)
            // stompClient.subscribe('/topic/greetings', function (message: any) {
            //     console.log(message);
            // })
            setIsLoaded(true)
            openGlobalSocket()
            // stompClient.subscribe()
        });
    }

    const openGlobalSocket = () => {
        if (isLoaded) {
            stompClient.subscribe("/topic/greetings", (message: { body: string; }) => {
                console.log('something')
                console.log(message);
            }, {});
        }
    }

    const send = () => {
        stompClient.send("/app/hello", {}, JSON.stringify({ 'name': 'name' }));
    }

    return (
        <>
            <button onClick={() => openConnection()}>KLIK</button>
            <button onClick={() => send()}>KLIK2</button>
        </>
    )
}

export default Simulator
