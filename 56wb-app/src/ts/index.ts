///<reference path='./../../node_modules/immutable/dist/Immutable.d.ts'/>
/// <reference path="../typings/react/react.d.ts" />

import Immutable = require('immutable');
import * as React from 'react';
import {Main} from './Main';

var map1: Immutable.Map<string, number>;
map1 = Immutable.Map({ a: 1, b: 2, c: 3 });
var map2 = map1.set('b', 50);
map1.get('b'); // 2
map2.get('b'); // 50


class Greeter<T> {
    greeting: T;
    constructor(message: T) {
        this.greeting = message;
    }
    greet() {
        return this.greeting;
    }
}


React.render(React.createElement(Main), document.getElementById('app'));


/***



async function main() {
 await ping();
}

async function ping() {
 for (var i = 0; i < 10; i++) {
  await delay(300);
  console.log("ping");
 }
}

function delay(ms: number) {
 return new Promise(resolve => setTimeout(resolve, ms));
}

main();

****/