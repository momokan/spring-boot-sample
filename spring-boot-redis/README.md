# Soring Boot Redis

## Redis のインストール

ソースをここからダウンロードする
  http://redis.io/

ビルドする

    $ tar xvfz ~/Downloads/redis-2.8.19.tar.gz
    $ cd redis-2.8.19
    $ make
    # make install

    # mkdir -p /usr/local/redis/{dump,logs}
    # chown -R momokan.momokan /usr/local/redis

設定ファイルを作成する

    $ cp redis.conf /usr/local/redis/

以下を修正する

    daemonize yes
    logfile "/usr/local/redis/logs/redis"
    dir /usr/local/redis/dump

起動スクリプトを作成する

    # cp utils/redis_init_script /etc/init.d/redis

以下を修正する

    CONF="/usr/redis/redis.conf"

## Redis を起動する

    # /etc/init.d/redis start

## Redis に接続してみる

    $ redis-cli -h localhost
    localhost:6379> set key1 hogehoge
    OK
    localhost:6379> get key1
    "hogehoge"
    localhost:6379> keys *
    1) "key1"

    参考: http://redis.shibu.jp/commandreference/


