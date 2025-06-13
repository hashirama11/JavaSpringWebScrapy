import { Connection } from './connection';
export declare class WebSocketConnection extends Connection {
    static HEARTBEAT_INTERVAL: number;
    socket?: any;
    canSend: boolean;
    constructor(url: string);
    onReload(_strategy: string): void;
    onUpdate(_path: string, _content: string): void;
    onMessage(_message: any): void;
    handleMessage(msg: any): void;
    handleError(msg: any): void;
    send(command: string, data: any): void;
}
