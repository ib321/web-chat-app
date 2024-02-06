import { Component, OnInit } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { ChatService } from '../chat.service';

@Component({
  selector: 'app-chatbox',
  templateUrl: './chatbox.component.html',
  styleUrls: ['./chatbox.component.css']
})
export class ChatboxComponent implements OnInit {
  title = 'app';

  greetings: string[] = [];
  showConversation: boolean = false;
  ws: any;
  name: string = "IB";
  disabled?: boolean;
  stompClient: any;

  constructor(private chatService: ChatService) { }

  ngOnInit() {
  }

  connect() {
    //connect to stomp where stomp endpoint is exposed
    //let socket = new SockJS("/shockjs");
    let socket = new WebSocket("ws://localhost:8085/greeting");
    this.ws = Stomp.over(socket);
    let that = this;
    this.ws.connect({}, function (frame: any) {
      that.ws.subscribe("/errors", function (message: any) {
        alert("Error " + message.body);
      });
      that.ws.subscribe("/topic/reply", function (message: any) {
        console.log(message)
        that.showGreeting(message.body);
      });
      that.disabled = true;
    }, function (error: any) {
      alert("STOMP error " + error);
    });
  }

  connect2shock() {
    //const socket = new SockJS('https://your-production-server/shockjs');
    //let socket = new SockJS("api/shockjs/");
    const socket = new SockJS('http://localhost:8085/shockjs');
    this.ws = Stomp.over(socket);
    let that = this;

    this.stompClient = Stomp.over(socket);

    const _this = this;
    _this.stompClient.connect({}, function(frame: any) {
      _this.stompClient.subscribe("/topic/reply", (message:any) => {
        if(message.body) {
          console.log(message.body);
        }
      });
    });
  }

  disconnect() {
    if (this.ws != null) {
      this.ws.ws.close();
    }
    this.setConnected(false);
    console.log("Disconnected");
  }

  sendName() {
    let data = JSON.stringify({
      'name': this.name
    })
    this.ws.send("/app/message", {}, data);
  }

  showGreeting(message: any) {
    this.showConversation = true;
    this.greetings.push(message);
    console.log(this.greetings);

  }

  setConnected(connected: any) {
    this.disabled = connected;
    this.showConversation = connected;
    this.greetings = [];
  }
  msg?: String;
  getValueFromServer() {
    this.chatService.getValueFromServer("pub").subscribe(
      (arg) => { 
        console.log(arg);
        this.msg = arg.msg;
        this.showGreeting(this.msg);
      }
    );
    
  }
}
