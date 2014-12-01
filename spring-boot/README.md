# Eclipse で開発する場合の注意

* src/main/resources に対して Maven Resource Plugin の filtering 機能を ON にしておくこと。  
  filtering 機能が有効でない場合、mvn eclipse:eclipse してインポートすると、Java Build Path の Sources に src/main/resources/\*\*/application.properties が設定される。[参考](http://d.hatena.ne.jp/imaginator/20140726/p1)  

* ホットデプロイを有効にする

  Spring Application の実行時に、VM 引数として以下を設定するとよい。  

    -Djava.system.class.loader=net.chocolapod.eclipseprojectloader.EclipseProjectClassLoader
    -javaagent:etc/lib/springloaded/springloaded-1.2.1.RELEASE.jar -noverify

  [Spring-Loaded](https://github.com/spring-projects/spring-loaded) で実行中にクラス定義を変更したクラスをホットデプロイできるようになり、  
  [EclipseProjectLoader](https://github.com/momokan/eclipse-project-loader) で src/main/resources 下のリソースを直接参照するようになる。  

  なお、リソースファイルの内容は各ライブラリでメモリ上にキャッシュされることが多いので、開発時にはそれらのキャッシュを切っておくこと。  
  たとえば Thymeleaf は src/main/resources/application.properties に  

    spring.thymeleaf.cache=false

  と記載することで読み込んだテンプレートをキャッシュしなくなる。  
  また、Hibernate の Validation 機能が利用するメッセージリソースはデフォルトでは src/main/resources/ValidationMessages.properties に記載されるが、  
  Hibernate Validation はこのプロパティファイルのキャッシュ機能を OFF にする手段が提供されていないため、ホットデプロイすることはできない。  


# TODO

* eclipse で開発する場合に properties ファイルやテンプレートを eclipse プロジェクトの classpath から探すため、eclipse 上でプロジェクトを refresh しないと反映されない。  
  これを回避するため、ClassLoader をファイルシステムから読み込む自作 ClassLoader に差し替える

  http://stackoverflow.com/questions/3801714/how-to-set-my-custom-class-loader-to-be-the-default

