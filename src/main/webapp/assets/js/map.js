/**
 * Created by geek1994 on 2015/11/26.
 */

/**
 * ��ͼ��ز���
 * @constructor
 */
function MapUtil(){

    /**
     *  ����û��ĵ�ǰλ�ã�����Point
     */
    function getLocation() {
        if(navigator.geolocation) {
            navigator.geolocation.getCurrentPosition()
        }
    }

    /**
     * ���ݴ�������Point���꣬��ʾ��ǰ�������ڵ�λ�á�
     * @param point id
     */
    function showPosition(point, id) {
        var map = new BMap.Map("baidu_map");
        var points = new BMap.Point(116.404, 39.915);
        map.centerAndZoom(points, 6);
    }
}

/**
 * �����ֻ꣬��longitude��latitude��
 * @constructor
 */
function Point() {
    var longitude;
    var latitude;
}