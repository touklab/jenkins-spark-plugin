jenkins-spark-plugin
====================

Trigger a call in your Spark.io device when build in Jenkins fails
=======
# Jenkins Spark.io Plugin

> A plugin that triggers your [Spark.io](http://spark.io) device when Jenkins build fails.

This plugin adds a post-build action which allows to execute a function on your Spark device. 
The call is invoked via a REST call to [Spark Cloud API](http://docs.spark.io/api/) so all you need is a Spark core connected to the cloud
and a head full of IoT ideas for your build failed notificator ;) 

## Configuration

## Sample Spark.io code
```
int pin = D0;

int trigger(String command);

void setup() {
    pinMode(pin, OUTPUT);
    Spark.function("trigger", trigger);
}

void loop() {
}

int trigger(String command) {
    digitalWrite(pin, HIGH);
    delay(400);
    digitalWrite(pin, LOW);
    return 1;
}
```

## See it in action!

[![ScreenShot](http://img.youtube.com/vi/qTfU8mA94W8/0.jpg)](https://www.youtube.com/watch?v=qTfU8mA94W8)

