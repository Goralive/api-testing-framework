node {

        stage("checkout repo") {
            git branch: 'master',
            credentialsId: 'your jenkins url',
            url: 'your git hub url'
        }

        stage("build"){
            sh "./gradlew clean api-test:assemble"
        }

        stage("run api test"){
                sh "./gradlew api-test:test -Dlogging=${LOGGING}"
        }
          allure([
            includeProperties: false,
            jdk: '',
            properties: [],
            reportBuildPolicy: 'ALWAYS',
            results: [[path: 'api/build/allure-results'],[path: 'ui/build/allure-results']]
            ])
}
