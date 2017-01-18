/**
 * Created by geek1994 on 2015/11/26.
 */

/**
 * 地图相关操作
 * @constructor
 */
function MapUtil(){

    /**
     *  获得用户的当前位置，返回Point
     */
    function getLocation() {
        if(navigator.geolocation) {
            navigator.geolocation.getCurrentPosition()
        }
    }

    /**
     * 根据传过来的Point坐标，显示当前座标所在的位置。
     * @param point id
     */
    function showPosition(point, id) {
        var map = new BMap.Map("baidu_map");
        var points = new BMap.Point(116.404, 39.915);
        map.centerAndZoom(points, 6);
    }
}

/**
 * 点坐标，只有longitude和latitude。
 * @constructor
 */
function Point() {
    var longitude;
    var latitude;
}