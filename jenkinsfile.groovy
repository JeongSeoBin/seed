pipeline {
    agent any
    environment {
        GIT_URL = "https://183.99.50.117/serverswdev2/server2_spring_was_seed.git"
        GIT_ID = "minkyou.kim"
        WEB_ROOT_PATH = "$WORKSPACE"
//        WEBAPP_PATH = "$WEB_ROOT_PATH/src/main/webapp"
        DOCKER_ROOT_PATH = "/home/docker_admin/docker/spring_was_seed"
        DOCKER_BUILD_PATH = "$DOCKER_ROOT_PATH/web/openjdk11"
        SSH_CONFIG_NAME = "Docker Container Server"
    }
    tools {
//        nodejs 'node16'
    	maven 'maven'
        jdk 'java17'
    }
    stages {
        stage('checkout') {
            steps {
                checkout([
                        $class: 'GitSCM',
                        branches: [[name: '*/master']],
                        doGenerateSubmoduleConfigurations: false,
                        userRemoteConfigs: [[
                                credentialsId: "${GIT_ID}",
                                url: "${GIT_URL}"
                            ]]
                        ])
                }
        }
        stage('Build') {
            stages {

//                stage('build - config copy') {
//                    steps {
//                        sh 'cp "${WEBAPP_PATH}/app/configServApp.js" "${WEBAPP_PATH}/app/configServ.js"'
//                    }
//                }
//
//                stage('build - npm install') {
//                    steps {
//                        sh 'cd ${WEBAPP_PATH} && npm install --legacy-peer-deps'
//                    }
//                }
//
//                stage('build - run build') {
//                    steps {
//                        sh 'cd ${WEBAPP_PATH} && npm run build:prod --max-old-space-size=8000'
//                    }
//                }

                stage('build - maven') {
                    steps {
                        sh 'mvn -f $WEB_ROOT_PATH/pom.xml clean install'
                    }
                }

                stage('build - copy') {
                    steps {
                        sh 'cp -Rf "$WEB_ROOT_PATH/target/\"${artifactId}\"-\"${version}\".jar" "$DOCKER_BUILD_PATH/\"${artifactId}\"-\"${version}\".jar"'
                    }
                }

            }
        }

        stage('Docker') {
            stages {
                stage('docker - compose rm') {
                    steps {
                        sshPublisher(
                            publishers: [
                                sshPublisherDesc(
                                    configName: "${SSH_CONFIG_NAME}",
                                    transfers: [
                                        sshTransfer(
                                            cleanRemote: false,
                                            excludes: '',
                                            execCommand: "cd ${DOCKER_ROOT_PATH} && docker-compose rm -fsv web",
                                        )
                                    ]
                                )
                            ]
                        )
                    }
                }

                stage('docker - image') {
                    steps {
                        sshPublisher(
                            publishers: [
                                sshPublisherDesc(
                                    configName: "${SSH_CONFIG_NAME}",
                                    transfers: [
                                        sshTransfer(
                                            cleanRemote: false,
                                            excludes: '',
                                            execCommand: "cd \"${DOCKER_BUILD_PATH}\" && sh Dockerbuild.sh $BUILD_NUMBER && docker image prune -f",
                                        )
                                    ]
                                )
                            ]
                        )
                    }
                }

                stage('docker - compose up') {
                    steps {
                        sshPublisher(
                            publishers: [
                                sshPublisherDesc(
                                    configName: "${SSH_CONFIG_NAME}",
                                    transfers: [
                                        sshTransfer(
                                            cleanRemote: false,
                                            excludes: '',
                                            execCommand: "cd ${DOCKER_ROOT_PATH} && docker-compose up -d web",
                                        )
                                    ]
                                )
                            ]
                        )
                    }
                }
            }
        }
   }
   post {
		always {
			recordIssues(
				enabledForFailure: false,
				failOnError: false,
                aggregatingResults: true,
                tools: [
                    java(),
                    spotBugs(pattern: '**/target/spotbugsXml.xml'),
                    pmdParser(pattern: '**/target/pmd.xml'),
                    cpd(pattern: '**/target/cpd.xml'),
                    checkStyle(pattern: '**/target/checkstyle-result.xml'),
                ],
			)
		}
   }
}
