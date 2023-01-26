// import SockJS from 'sockjs-client';
// import * as Stomp from '@stomp/stompjs';
import axios from 'axios';
import { parse } from 'path';
import { useEffect, useState } from 'react';
import SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { MapSimulator } from './map.component';

export const Simulator = () => {
    const [isLoaded, setIsLoaded] = useState();
    const [locator, setLocator] = useState({ latitude: 45.273252, longitude: 19.782898 })
    var stompClient: any;

    const openConnection = () => {
        let ws = new SockJS('http://localhost:8000/socket');
        stompClient = Stomp.over(ws);

        stompClient.connect({}, function (frame: any) {
            console.log(frame)
            stompClient.subscribe('/topic/greetings', function (message: any) {
                console.log(message.body)
                const parsed = JSON.parse(message.body)
                setLocator({ longitude: parsed.longitude, latitude: parsed.latitude })
            })
        });
    }

    useEffect(() => {
        openConnection()
    }, [])

    return (
        <>
            <MapSimulator locator={locator} />
            <button onClick={() => openConnection()}>KLIK</button>
        </>
    )
}

export default Simulator
