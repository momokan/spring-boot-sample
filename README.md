# Eclipse で開発する場合の注意

* src/main/resources に対して Maven Resource Plugin の filtering 機能を ON にしておくこと。  
  filtering 機能が有効でない場合、mvn eclipse:eclipse してインポートすると、Java Build Path の Sources に src/main/resources/\*\*/application.properties が設定される。

  http://d.hatena.ne.jp/imaginator/20140726/p1


# TODO

* eclipse で開発する場合に properties ファイルやテンプレートを eclipse プロジェクトの classpath から探すため、eclipse 上でプロジェクトを refresh しないと反映されない。
  これを回避するため、ClassLoader をファイルシステムから読み込む自作 ClassLoader に差し替える

  http://stackoverflow.com/questions/3801714/how-to-set-my-custom-class-loader-to-be-the-default

