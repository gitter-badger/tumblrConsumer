<template>
  <v-container fill-height>
    <v-layout row wrap>
      <v-flex class="ma-2 text-xs-center">
        <h4>Post Retrieval Monitor</h4>
        <v-btn light large class="amber" 
          @click.prevent="connect" 
          :disabled="connected == true">
          Start
        </v-btn>
        <v-btn light large class="ma-2 amber" 
          @click.prevent="disconnect" 
          :disabled="connected == false">
        Stop
        </v-btn>
      </v-flex>
      <div class="container">
        <div class="large-12 medium-12 small-12 cell">
            <v-file-input id="file" label="file" v-on:change="handleFileUpload()"></v-file-input>
            <v-btn light large class="ma-2 amber" v-on:click="submitFile()">Submit</v-btn>
        </div>
      </div>
      <v-container class="grey lighten-5">
        <v-row no-gutters>
          <v-col
            v-for="item in received_messages" :key="item"
            cols="12"
            sm="4"
          >
            <v-card
              class="pa-2"
              outlined
              tile
              v-html="item.body"
            >
              
            </v-card>
          </v-col>
        </v-row>
      </v-container>
          <!--<v-flex xs8 offset-md2>-->
          <v-flex xs12 offset-md2>
            <v-card class="mx-auto"
                  max-width="344"
                  outlined> 
<!--
<h3>Filled bar</h3>
<VmProgress 
percentage="80" 
stroke-width="14" 
stroke-color="green" 
track-color="#8BC34A" 
text-inside="true"
    >
</VmProgress>-->
              
              <v-list-item three-line>
                <v-list-item-content>
                  <div class="overline mb-4">DETAILS</div>
                  <v-list-item-title class="headline mb-1">Metadata</v-list-item-title>
                  <v-list-item-subtitle>Greyhound divisely hello coldly fonwderfully</v-list-item-subtitle>
                  <v-list-item-subtitle>
                    <v-progress-linear
                      v-model="skill"
                      color="blue-grey"
                      height="25"
                      reactive
                    >
                      <template v-slot="{ value }">
                        <strong>{{ Math.ceil(value) }}%</strong>
                      </template>
                    </v-progress-linear>
                  </v-list-item-subtitle>
                  <v-divider></v-divider>
                  <v-list-subtitle>
                    Tags
                    <v-spacer></v-spacer>
                    <v-chip
                      class="ma-2"
                      color="secondary"
                    >
                      Secondary
                    </v-chip>
                  </v-list-subtitle>
                  <v-list-subtitle>
                    <v-text-field
                      v-model="label"
                      label="Label"
                    ></v-text-field>
                    <v-text-field
                      v-model="hint"
                      label="Hint"
                    ></v-text-field>
                    <v-text-field
                      v-model="placeholder"
                      label="Placeholder"
                    ></v-text-field>
                  </v-list-subtitle>
                </v-list-item-content>
          
                <v-list-item-avatar
                  tile
                  size="80"
                  color="grey"
                ></v-list-item-avatar>
              </v-list-item>
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
            <!--
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
                </v-card-text>
              </v-card>
            </div>-->
          </v-flex>
        </v-layout>
  </v-container>
</template>

<script>

import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import axios from 'axios';


export default {
  name: "Progress",
  data() {
    return {
      received_messages: [],
      send_message: null,
      connected: false,
      skill: 1,
      file: ''
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
    handleFileUpload(){
      this.file = this.$refs.file.files[0];
    },
    submitFile(){
      let formData = new FormData();
      formData.append('file', this.file);
      axios.post( '/single-file', formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
      }).then(function(){
        console.log('SUCCESS!!');
      })
      .catch(function(){
        console.log('FAILURE!!');
      });
    },
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