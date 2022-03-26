# Finagle Guide

## Resources
1. [Repo](https://github.com/twitter/finagle)

## Repo
You can run the finagle code in a scala repo, you can use [dodo](https://github.com/twitter/dodo) to build it.

```shell
$ cd finagle
$ git checkout master
$ curl -s https://raw.githubusercontent.com/twitter/dodo/develop/bin/build | bash -s -- --no-test finagle
$ ./sbt "project finagle-http" console
 ...build output...
scala>
```

