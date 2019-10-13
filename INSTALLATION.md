# How to install bomberman into your Slack workspace

## Create a Slack app

First go to https://api.slack.com/apps?new_app=1.

And choose the name you want to give to `bomberman` app and the `Slack workspace`.


![create-slack-app-modal](https://i.imgur.com/VqAGo0Q.png)


Then, you will be redirected to this page:

![app-home-page](https://i.imgur.com/FTuhs1k.png)

We will need to add some permissions so that `bomberman` will be able to post messages on to a channel of choice.

## Add permissions to the app

### Incoming Webhooks

First select the `Incoming Webhooks` feature/functionality, and activate it:

![incoming-webhooks-page](https://i.imgur.com/Beyujul.png)

Click the button `Add New Webhook to Workspace`.

And choose the `channel` you want `bomberman` to be able to post its messages.

![add-bomberman-to-channel](https://i.imgur.com/3tySwmC.png)

Then copy the new webhook URL made, we will use it later.

![webhook-url-example](https://i.imgur.com/aF5RD4k.png)

### Setup bomberman service

Before setting the Slack's Slash Command, you will need to host `bomberman` somewhere, like AWS, Digital Ocean, Google Cloud, Heroku, now.sh, etc.

I recommend running the project using `Docker`, it will run a HTTP server on port 8080.

### Slash Commands

Back into the `Basic Information` page of your Slack app, click the `Slash Commands` feature/functionality.

![slash-commands-page](https://i.imgur.com/iKkluWn.png)

Then create a new command!

![create-new-command-page](https://i.imgur.com/SyGvF1W.png)

It is important to remember, that the `Request URL` is where you are hosting `bomberman`. This URL will be used by Slack to send a POST request to your `bomberman` service.

## Now everything should be working!

If you send a Slash command like this on the channel you've set up:

![bomberman-slack-slash-command](https://i.imgur.com/yY3YWeT.png)

This should happen:

![command-response](https://i.imgur.com/6CK6anb.png)
