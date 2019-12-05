node {

        stage("checkout repo") {
            git branch: 'master',
            credentialsId: '615f10ab-dfbe-40d3-8724-0fe7ba578e95',
            url: 'https://github.com/Goralive/api-testing-framework.git'
        }

        stage("build"){
            sh "./gradlew clean api-test:assemble"
        }

        stage("run api test"){
            sh "./gradlew api-test:test"
        }

        allure([
            includeProperties: false,
            jdk: '',
            properties: [],
            reportBuildPolicy: 'ALWAYS',
            results: [[path: 'api/build/allure-results'],[path: 'ui/build/allure-results']]
            ])
}