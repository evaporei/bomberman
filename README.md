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

## Development

To setup a local enviroment for development you will need either `leiningen` or `Docker`.

After cloning the repository and changing to its directory you can run it like this:

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
