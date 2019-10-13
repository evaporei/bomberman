# bomberman

> :bomb: A slack bot that gets images and post them

Sending a Slash Command like this:

![bomberman-slack-slash-command](https://i.imgur.com/yY3YWeT.png)

Results in this:

![command-response](https://i.imgur.com/6CK6anb.png)

This project is heavly inspired by `hubot`'s `pugme`, for more info look [here](https://github.com/hubot-scripts/hubot-pugme).

## How to install bomberman into your Slack workspace

See the tutorial [here](INSTALLATION.md).

## How to use it on your workspace

Just send a `/bomberman [breed/race] [quantity]` Slash command message on to the channel you've configured `bomberman`, to receive the cute animals pictures :wink: 

Some examples:

```
/bomberman pug 2
/bomberman shiba 5
```

For now this project only works for `dogs`, and the list of the accepted breeds is [here](https://dog.ceo/dog-api/breeds-list).

## Running the project

Before everything, you will need to create a `.env` file containing a line with the Slack URL `bomberman` will use to actually send messages to a channel. On this repository there is a file called [.env.example](https://github.com/otaviopace/bomberman/blob/master/.env.example) to show how this file should be. To actually get the Slack URL look at [this part](https://github.com/otaviopace/bomberman/blob/master/INSTALLATION.md#incoming-webhooks) of the `INSTALLATION.md` tutorial.

To run the project you will need either `leiningen` or `Docker`.

### Leiningen

```
lein run
```

### Docker

```
docker build -t bomberman .
docker run -it --rm -p 8080:8080 bomberman
```

## License
>You can check out the full license [here](https://github.com/otaviopace/bomberman/blob/master/LICENSE.md)

This project is licensed under the terms of the **WTFPL** license.
You just DO WHAT THE FUCK YOU WANT TO.
