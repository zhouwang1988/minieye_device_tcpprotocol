a=$(ps -ef | grep tsinghua_device_tcpprotocol-0.0.1-SNAPSHOT.jar | grep -v grep |grep -v bash | awk '{ print $2 }')
wait
if [ -n "$a" ];then
        echo "platfrom is running";
else
        nohup java -jar ./tsinghua_device_tcpprotocol-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &
wait
fi
