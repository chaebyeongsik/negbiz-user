/*
 * json data 셈플
 *  [ 
 *      { "label" : "명칭1", "value" : 0.3},
 *      { "label" : "명칭2", "value" : 0.1},
 *      { "label" : "명칭3", "value" : 0.15},
 *      { "label" : "명칭4", "value" : 0.45}
 *  ];
 *  
 * option 셈플은 아래 defaults 를 참조하며, 필요한 것만 정의하여 options 인자로 전달
 *  var opChartOption = {
 *      target : "opPieChartDiv",
 *      dataUrl : "/intra/path/to/jsonData.do" 
 *  }
 *  
 *  실행
 *  opChartPie(opChartOption);
 */
var opChartPie = function(options, chart) {
    // 기본 옵션 및 옵션 설정
    var defaults = {
        // div id
        target : "opPieChartDiv",
        // pie 또는 donut
        graphType : "pie",
        // 그래프 상단 제목 표시 [{size : 글자크기숫자, text : "제목 문자열" }]
        title : [],
        // 그래프를 그리는 속도 1을 주면 1초 동안 그림 애니메이션 효과
        duration : 0,
        // [{label : 타이틀, value : 값}] 형식의 JSON 타입 데이터
        data : null,
        // ajax로 데이터를 반환하는 경우 형식은 위와 동일
        dataUrl : null,
        // 그래프가 표시될 div의 최소 높이
        height : "400px",
        // 그래프가 표시될 div의 넓이
        width : "100%",
        // 기본 테마 '', 'patterns', 'chalk', 'dark', 'light', 'black' 중 택일
        theme : "light",
        // 배경색 지정, 단 dark, black는 배경색이 자동 설정되어 변경됨
        bgColor : "#ffffff",
        // 배경 이미지를 사용할 경우 설정
        bgImage : null,
        // 3D로 그래프를 표시
        use3D : false,
        // 하단에 legend 사용여부
        useLegend : true,
        // 데이터 출력기능 사용여부
        export : false,
        // 타이틀 위치 inner/outer 중 선택
        labelPosition : "outer",
        // 마우스 오버시 툴팁 모양 title value percents 는 고정값으로 변경 불가
        balloonText : "[[title]]<br><b>[[value]]</b> ([[percents]]%)"
    };
    if(!options) {
        options = {};
    }
    this.settings = $.extend(true, {}, defaults, options);

    if(!this.settings.data && !this.settings.dataUrl) {
        opErrorMsg("Chart Data가 없습니다.");
    } else {
        // 이전 챠트 초기화
        if(chart) {
            chart.clear();
        }
        // 기본 스타일
        var style = "min-height: " + this.settings.height + ";";
        if(this.settings.width) {
            style += "width : " + this.settings.width + ";";
        }
        if(this.settings.bgColor && !this.settings.bgImage) {
            style += "background-color:" + this.settings.bgColor + ";";
        }
        if(this.settings.bgImage) {
            style += "background-image:url(" + this.settings.bgImage + ");";
        }

        $("#" + this.settings.target).attr("style", style);

        var chartData = [];
        if(this.settings.data) {
            chartData = this.settings.data;
        } else {
            $.ajax({
                dataType : "json",
                async : false,
                url : this.settings.dataUrl,
                success : function(datas) {
                    chartData = datas;
                }
            });
        }

        var chartOption = {
            type : "pie",
            theme : this.settings.theme,
            dataProvider : chartData,
            titleField : "label",
            valueField : "value",
            balloonText : this.settings.balloonText
        };

        // 애니메이션 효과
        chartOption.startDuration = this.settings.duration;
        if(this.settings.duration > 0) {
            chartOption.sequencedAnimation = true;
        } else {
            chartOption.sequencedAnimation = false;
        }
        // 그래프 상단에 제목을 표시
        if(this.settings.title) {
            chartOption.titles = this.settings.title;
        }
        // 3D 사용여부
        if(this.settings.use3D) {
            chartOption.depth3D = 15;
            chartOption.angle = 30;
        }
        // 파일출력 여부
        if(this.settings.export) {
            chartOption.export = {
                enabled : true
            };
        }
        // 하단에 legend을 표시
        if(this.settings.useLegend) {
            chartOption.legend =  {
                align : "center",
                markerType : "circle"
            };
        }
        // 도넛 유형 적용
        if(this.settings.graphType == "donut") {
            chartOption.innerRadius = "30%";
        }
        // 레이블 위치
        if(this.settings.labelPosition == "outer") {
            chartOption.labelRadius = 30;
            chartOption.labelText = "[[title]]: [[value]]";
        } else {
            chartOption.labelRadius = -30;
            chartOption.labelText = "[[percents]]%";
        }

        return AmCharts.makeChart(this.settings.target, chartOption);
    }
};
