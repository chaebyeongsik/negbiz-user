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
 *  opChartMixed(opChartOption);
 */
var opChartMixed = function(options, chart) {
    // 기본 옵션 및 옵션 설정
    var defaults = {
        // div id
        target : "opMixedChartDiv",
        // mix 모드에서는 세로(mixcolumn) / 가로(mixbar) / 쌓기(mixstack) 로 구분
        graphType : "mixbar",
        // 기준선 데이터 key 명칭
        categoryNm : "label",
        // 그래프 상단 제목 표시 [{size : 글자크기숫자, text : "제목 문자열" }]
        title : [],
        // 좌측 기준 축 제목 [{id : "id값", title : "제목 문자열"}]
        categoryTitle : [ {
            id : "",
            position : "bottom",
            title : ""
        } ],
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
        // 상단스크롤바 사용
        useScroll : false,
        // 마우스포인트에 가로세로 기준선 생성
        useCursor : false,
        // 라인 굵기 (line 그래프 일 경우에만 사용)
        lineThickness : 2,
        // 마우스 오버시 툴팁 모양
        balloonText : "[[title]]<br><b>[[value]]</b>"
    };
    if(!options) {
        options = {};
    }
    this.settings = $.extend(true, {}, defaults, options);

    if(!this.settings.data && !this.settings.dataUrl) {
        opErrorMsg("Chart Data가 없습니다.");
    } else if(!this.settings.graphs) {
        opErrorMsg("Chart를 구성할 개별 graphs Data가 없습니다.");
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
            type : "serial",
            theme : this.settings.theme,
            dataProvider : chartData,
            categoryField : this.settings.categoryNm,
            startDuration : 1,
            categoryAxis : {
                gridPosition : "start",
                minorGridEnabled : true,
            },
            valueAxes : [],
            trendLines : [],
            guides : [],
            allLabels : [],
            balloon : {},
            graphs : this.settings.graphs
        };

        // 라인 그래프가 함께 존재시
        for(var i = 0 ; i < chartOption.graphs.length ; i++) {
            var graph = chartOption.graphs[i];
            if(graph.type == "line") {
                graph.fillAlphas = 0;
                graph.bullet = "round";
                graph.lineThickness = this.settings.lineThickness;
            } else if(graph.type == "smoothedLine") {
                graph.fillAlphas = 0;
                graph.bullet = "round";
                graph.lineThickness = this.settings.lineThickness;
            }
        }

        // 애니메이션 효과
        chartOption.startDuration = this.settings.duration;
        if(this.settings.duration > 0) {
            chartOption.sequencedAnimation = true;
        } else {
            chartOption.sequencedAnimation = false;
        }
        // 마우스커서 사용
        if(this.settings.useCursor) {
            chartOption.chartCursor = {
                enabled : true
            };
        }
        // 그래프 상단에 제목을 표시
        if(this.settings.title) {
            chartOption.titles = this.settings.title;
        }
        // 그래프 좌측기준선 제목 표시
        if(this.settings.categoryTitle) {
            chartOption.valueAxes = this.settings.categoryTitle;
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
        if(this.settings.useLegend && this.settings.categoryTitle[0].title) {
            chartOption.legend = {
                useGraphSettings : true
            };
        }
        // 상단 스크롤바 사용여부
        if(this.settings.useScroll) {
            chartOption.chartScrollbar = {
                enabled : true
            };
        }
        // 바/컬럼 & 스텍여부에 따른 설정
        if(this.settings.graphType == "mixbar") {
            chartOption.rotate = true;
        } else if(this.settings.graphType == "mixstackbar") {
            chartOption.rotate = true;
            chartOption.valueAxes[0].stackType = "regular";
        } else if(this.settings.graphType == "mixstackcolumn") {
            chartOption.valueAxes[0].stackType = "regular";
        }

        return AmCharts.makeChart(this.settings.target, chartOption);
    }
};
