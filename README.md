# chatbot-api
ChatGpt AI 问答助手Demo

1、关于docker镜像打包
（1）如果使用远程的Linux服务器，写好 Dockerfile文件，然后配置好远程的云服务器信息（edit 的时候添加一个远程的Docker连接），
将Dockerfile文件和远程的云服务器绑定，执行 Dockerfile，就会将本地 jar 包推送的远程服务器，并执行；

（2） build.sh 和 start.sh 的作用和 Dockerfile 一样，用于构建镜像和执行，当使用本地 Docker 的时候，使用这两个文件