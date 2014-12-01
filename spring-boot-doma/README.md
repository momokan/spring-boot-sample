# Spring boot doma

## doma の開発準備

* Eclipse の Install New Software メニューから `http://eclipse.seasar.org/updates/3.5/` 内の doma tool をインストールする
* Eclipse プロジェクトのプロパティにて以下を設定する
    * Java Compiler > Annotation Processing にて Enable project settings を有効にする  
      Generated source directory に .apt_generated が設定される
    * Java Compiler > Annotation Processing > Factory Path にて Add External Jar から maven ローカルリポジトリ内の doma-2.0.1.jar を追加する

## データベースの初期化

Sqlite3 でローカルファイルシステム上にデータベースを作る場合、あらかじめデータベースを作成しておく必要がある。

プロジェクトトップディレクトリにて

    $ etc/sqlite3/init.sh

で databaseDir/dabase1.db が作成される。



