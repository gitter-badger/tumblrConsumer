<template>
  <v-container fill-height>
    <v-layout row wrap>
          <v-flex class="text-xs-center">
            <h4>Post Retrieval Monitor</h4>
            <v-btn light large class="amber" 
            @click.prevent="connect" 
            :disabled="connected == true">
            Start
            </v-btn>
            <v-btn light large class="amber" 
            @click.prevent="disconnect" 
            :disabled="connected == false">
            Stop
            </v-btn>
          </v-flex>
          <v-flex xs8  offset-md2>
            <v-card>
              <v-card-actions>
                  <v-btn icon class="red--text">
                    <v-icon small>fa-reddit</v-icon>
                  </v-btn>
                  <v-btn icon class="light-blue--text">
                    <v-icon small>fa-twitter</v-icon>
                  </v-btn>
                  <v-btn icon class="blue--text text--darken-4">
                    <v-icon small>fa-facebook</v-icon>
                  </v-btn>
                </v-card-actions>
            </v-card>
            <div v-for="item in received_messages" :key="item">
              <v-card class="my-3" hover>
                <v-card-media>
                  <v-container fill-height fluid>
                    <v-layout>
                      <v-flex xs12 align-end d-flex>
                        <span class="headline">{{ item.title }}</span>
                      </v-flex>
                    </v-layout>
                  </v-container>
                </v-card-media>
                <v-card-text v-html="item.body">
                  <!--{{ item.body }}-->
                </v-card-text>
              </v-card>
            </div>
          </v-flex>
        </v-layout>
  </v-container>
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default {
  name: "Progress",
  data() {
    return {
      received_messages: [],
      send_message: null,
      connected: false
    };
  },
  computed: {
    postBody(jsonObj) {
      //return "<pre>" + JSON.stringify(jsonObj, null, 2) + "</pre>";
      //return JSON.stringify(jsonObj, null, 2);
      return jsonObj.body;
    }
  },
  methods: {
    send() {
      console.log("Send message:" + this.send_message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = { name: this.send_message };
        this.stompClient.send("/app/toServer", JSON.stringify(msg), {});
      }
    },
    connect() {
      /*
      The connect() function uses SockJS and stomp.js to open a connection to /gs-guide-websocket, 
      which is where our SockJS server waits for connections. 
      Upon a successful connection, 
      the client subscribes to the /topic/greetings destination, where the server will publish greeting messages.
      */
      this.socket = new SockJS("http://localhost:8080/gs-guide-websocket");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
        {},
        frame => {
          this.connected = true;
          console.log(frame);
          this.stompClient.subscribe("/topic/progress", tick => {
            //console.log(tick);
            //console.log(tick.body);
            //console.log(tick.body.content);
            //console.log(JSON.parse(tick.body))
            //var jsonObj = JSON.parse(tick.body).content;
            var jsonObj = JSON.parse(tick.body);

            this.received_messages.push(jsonObj);
            //this.received_messages.push(JSON.stringify(jsonObj, null, 2));
            //this.received_messages.push(tick.body);
          });
        },
        error => {            
          console.log(error);           
          this.connected = false;
        }
      );
    },
    disconnect() {
      if (this.stompClient) {
        this.stompClient.disconnect();
      }
      this.connected = false;
    },
    tickleConnection() {
      this.connected ? this.disconnect() : this.connect();
    }
  },
  mounted() {
    this.connect();
  }
};
</script>

<style scoped>

</style>